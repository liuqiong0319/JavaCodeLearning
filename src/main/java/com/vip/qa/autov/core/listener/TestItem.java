package com.vip.qa.autov.core.listener;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TestItem {

    //测试类名
    @JSONField(name = "class")
    private String clazz;
    //测试方法名
    private String method;
    //测试描述
    private String description;
    //测试状态：PASS(1)|FAIL(2)|SKIP(3)|SUCCESS_PERCENTAGE_FAILURE(4)
    private String status;
    //testNG测试状态码，见ITestResult
    private int statusCode;
    //测试执行持续时间，单位毫秒
    private long durationMs;
    //测试开始执行时间
    private String startedAt;
    //测试执行结束时间
    private String finishedAt;
    //是否数据驱动
    @JSONField(name = "isDataDriven")
    private boolean isDataDriven;
    //如果是数据驱动，则表示数据序号，否则为空
    private Integer sequence;
    //dataProvider
    private String dataProvider;
    //测试方法参数
    private Object[] parameters;
    //测试异常
    private Exception exception;
    //Reporter输出日志
    private List<String> reporterOutput;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public boolean isDataDriven() {
        return isDataDriven;
    }

    public void setDataDriven(boolean dataDriven) {
        isDataDriven = dataDriven;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(String dataProvider) {
        this.dataProvider = dataProvider;
    }

    public List<String> getReporterOutput() {
        return reporterOutput;
    }

    public void setReporterOutput(List<String> reporterOutput) {
        this.reporterOutput = reporterOutput;
    }

    public static class Exception {
        //异常类型名称
        @JSONField(name = "class")
        private String clazz;
        //异常message
        private String message;
        //完整异常堆栈
        private String fullStacktrace;
        //简短异常堆栈
        private String shortStacktrace;

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getFullStacktrace() {
            return fullStacktrace;
        }

        public void setFullStacktrace(String fullStacktrace) {
            this.fullStacktrace = fullStacktrace;
        }

        public String getShortStacktrace() {
            return shortStacktrace;
        }

        public void setShortStacktrace(String shortStacktrace) {
            this.shortStacktrace = shortStacktrace;
        }
    }
}
