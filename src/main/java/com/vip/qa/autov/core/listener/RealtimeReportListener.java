package com.vip.qa.autov.core.listener;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.vip.qa.autov.core.json.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 每一个测试用例执行完成，将测试结果发送到vqp实时展示
 */
public class RealtimeReportListener implements ITestListener {

    private static Logger logger = LoggerFactory.getLogger(RealtimeReportListener.class);

    public static final String FMT_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";

    private static ThreadLocal<SimpleDateFormat> formater = new ThreadLocal<>();

    private static Map<Method, AtomicInteger> methodCounter = Maps.newConcurrentMap();

    private static BlockingQueue<TestItem> queue = Queues.newLinkedBlockingQueue();

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        generateReport(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        generateReport(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        generateReport(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        generateReport(result);
    }

    @Override
    public void onStart(ITestContext context) {
        executor.submit(new SendReportTask());
        logger.info("Start sending realtime report!");
    }

    @Override
    public void onFinish(ITestContext context) {
        executor.shutdownNow();
        logger.info("Stop sending realtime report!");
    }

    private void generateReport(ITestResult result) {
        TestItem item = new TestItem();
        item.setClazz(result.getMethod().getRealClass().getName());
        item.setMethod(result.getMethod().getMethodName());
        String description = result.getMethod().getDescription();
        if (!Utils.isStringEmpty(description)) {
            item.setDescription(description);
        }
        item.setStatusCode(result.getStatus());
        item.setStatus(getStatusString(result.getStatus()));

        SimpleDateFormat sdf = getFormater();
        String startTime = sdf.format(result.getStartMillis());
        String endTime = sdf.format(result.getEndMillis());
        item.setStartedAt(startTime);
        item.setFinishedAt(endTime);
        long duration = result.getEndMillis() - result.getStartMillis();
        item.setDurationMs(duration);

        Object[] parameters = result.getParameters();
        boolean dataDriven = parameters != null && parameters.length > 0;
        item.setParameters(parameters);
        item.setDataDriven(dataDriven);
        if (dataDriven) {
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            AtomicInteger counter = methodCounter.get(method);
            if (counter == null) {
                counter = new AtomicInteger();
                methodCounter.put(method, counter);
            }
            item.setSequence(counter.incrementAndGet());
        }

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            TestItem.Exception exception = new TestItem.Exception();
            item.setException(exception);
            exception.setClazz(throwable.getClass().getName());

            if (!Utils.isStringEmpty(throwable.getMessage())) {
                exception.setMessage(throwable.getMessage());
            }

            String[] stackTraces = Utils.stackTrace(throwable, false);
            exception.setShortStacktrace(stackTraces[0]);
            exception.setFullStacktrace(stackTraces[1]);
        }

        List<String> output = Reporter.getOutput(result);
        List<String> notNullLines = new ArrayList<String>();
        for (String line : output) {
            if (line != null) {
                notNullLines.add(line);
            }
        }
        if (notNullLines.size() > 0) {
            item.setReporterOutput(notNullLines);
        }

        queue.add(item);
    }


    public static String getStatusString(int testResultStatus) {
        switch (testResultStatus) {
            case ITestResult.SUCCESS:
                return "PASS";
            case ITestResult.FAILURE:
                return "FAIL";
            case ITestResult.SKIP:
                return "SKIP";
            case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
                return "SUCCESS_PERCENTAGE_FAILURE";
            default:
                throw new AssertionError("Unexpected value: " + testResultStatus);
        }
    }

    private static SimpleDateFormat getFormater() {
        SimpleDateFormat sdf = formater.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(FMT_DEFAULT);
            formater.set(sdf);
        }
        return sdf;
    }

    private static class SendReportTask implements Runnable {

        @Override
        public void run() {
            sendReport();
        }

        private void sendReport() {
            boolean testFinished = false;
            while (!testFinished || queue.size() > 0) {
                try {
                    TestItem item = queue.take();
                    String json = JsonMapper.toJsonPretty(item);
                    System.err.println(json);
                } catch (InterruptedException e) {
                    testFinished = true;
                    logger.info("Ready to stop until finish sending all the report items!");
                } catch (Exception e) {
                    logger.error("Send report item error, message:", e.getMessage());
                }
            }
        }

    }

}
