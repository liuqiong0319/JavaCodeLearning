package java.learning.work;

import com.eviware.soapui.impl.wsdl.WsdlInterface;
import com.eviware.soapui.impl.wsdl.WsdlOperation;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlImporter;
import com.eviware.soapui.model.iface.Operation;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


//java使用soapui解析wsdl
public class ImportWsdl {
    public static Map<String, HashMap<String, String>> result = new HashMap();

    public List<WsMethodInfo> getProBySoap(String address) throws Exception {
        URL url = new URL(address);
        InputStream openStream = url.openStream();
        openStream.close();

        Map<String,Object> result = new HashMap<String,Object>();
        WsdlProject project = new WsdlProject();
        WsdlInterface[] wsdls = WsdlImporter.importWsdl(project, address);

        WsdlInterface wsdl = wsdls[0];
        List<Operation> operationList = wsdl.getOperationList();
        for (int i = 0; i < operationList.size(); i++) {
            Operation operation = operationList.get(i);
            WsdlOperation op = (WsdlOperation) operation;
            Map<String,Object> tmp = new HashMap<String,Object>();
            tmp.put(op.getName(), op.createRequest(true));
            result.put(i+"", tmp);
        }

        if ((result != null) && (result.size() != 0)) {
            List importWsdl = new ArrayList();
            Set keySet = result.keySet();
            Iterator iterator = keySet.iterator();
            while (iterator.hasNext()) {
                WsMethodInfo info = new WsMethodInfo();
                List inputType = new ArrayList();
                List inputNames = new ArrayList();
                List isparent = new ArrayList();
                String next = ""+ iterator.next();
                HashMap hashMap = (HashMap) result.get(next);
                Set keySet2 = hashMap.keySet();
                Iterator iterator2 = keySet2.iterator();
                if (iterator2.hasNext()) {
                    String next2 = (String) iterator2.next();
                    String string = (String) hashMap.get(next2);
                    //处理targetNameSpace
                    String qname=string.substring(string.lastIndexOf("\"http://")+1,string.lastIndexOf("\">"));
                    info.setTargetNameSpace(qname);

                    String soap11 = "http://schemas.xmlsoap.org/soap/envelope";
                    String soap12 = "http://www.w3.org/2003/05/soap-envelope";
                    InputStreamReader is = new InputStreamReader(new ByteArrayInputStream(string.getBytes("utf-8")));
                    BufferedReader ibr = new BufferedReader(is);
                    String readLine = ibr.readLine();
                    if (readLine != null) {
                        if (readLine.indexOf(soap11) >= 0)
                            info.setTargetXsd("11");
                        else if (readLine.indexOf(soap12) >= 0) {
                            info.setTargetXsd("12");
                        }
                    }
                    ibr.close();
                    is.close();

                    info.setMethodSoapAction(string);

                    Document read = DocumentHelper.parseText(string);
                    Element rootElement = read.getRootElement();
                    List<Element> elements = rootElement.elements();
                    for (Element element : elements) {
                        if ("Body".equals(element.getName())) {
                            List<Element> elements2 = element.elements();
                            info.setMethodName(next2);
                            for (Element element2 : elements2) {
                                getParameter(element2, 1, 1, inputType, inputNames, isparent);
                                info.setInputNames(inputNames);
                                info.setInputType(inputType);
                                info.setOutputType(isparent);
                            }
                        }
                    }

                    importWsdl.add(info);
                }
            }
            return importWsdl;
        }
        return null;
    }

    public void getParameter(Element element2, int gen, int genParent, List<String> inputType, List<String> inputNames,
                             List<String> isparent) {
        if (element2 != null) {
            List<Element> elements3 = element2.elements();
            if ((elements3 != null) && (elements3.size() != 0))
                for (Element element : elements3) {
                    inputType.add(gen + "," + genParent);
                    inputNames.add(element.getQualifiedName());
                    if (element != null) {
                        List e = element.elements();
                        if ((e != null) && (e.size() != 0)) {
                            isparent.add("1");
                            int gen1 = gen + gen;
                            getParameter(element, gen1, gen, inputType, inputNames, isparent);
                        } else {
                            isparent.add("0");
                        }
                    }
                }
        }
    }
}