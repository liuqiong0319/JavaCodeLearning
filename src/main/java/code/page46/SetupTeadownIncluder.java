package code.page46;

/**
 * Created by qiong.liu on 2018/10/30.
 */
public class SetupTeadownIncluder {
    private PageData pageData;
    private boolean isSuite;
    private WikiPage testPage;
    private StringBuffer newPageContent;
    private PageCrawler pageCrawler;

    public static String render(PageData pageData) throws Exception{
        return render(pageData,false);
    }

    public static String render(PageData pageData, boolean isSuite) throws Exception {
        return new SetupTeadownIncluder(pageData).render(isSuite);
    }
    
    private SetupTeadownIncluder(PageData pageData){
        this.pageData=pageData;
        testPage=pageData.getWikiPage();
        pageCrawler=testPage.getPageCrawler();
        newPageContent=new StringBuffer();
    }
    
    private String render(boolean isSuite) throws Exception {
        this.isSuite = isSuite;
        if (isTestPage()) {
            includeSetupAndTeardownPages();
        }
        return pageData.getHtml();
    }




    private boolean isTestPage() throws Exception{
        return pageData.hasAttribute("Test");
    }

    private void includeSetupAndTeardownPages() throws  Exception{
        includeSetUpPages();
        includePageContent();
        includeTearDownPage();
        updatePageContent();
    }

    private void updatePageContent() {
    }


    private void includeTearDownPage() {
    }

    private void includePageContent() {
    }



    private void includeSetUpPages() throws Exception {
        if (isSuite)
            includeSuiteSetUpPages();
        includeSetUpPages();
    }

    private void includeSuiteSetUpPages() throws Exception{
        include(SuiteResponder.SUITE_SETUP_NAME,"-setup");
    }

    private void include(String pageName,String arg) throws Exception{
        WikiPage inheritePage=findInheritedPage(pageName);
        if (inheritePage!=null){
            String pagePathName=getPathNameForPage(inheritePage);
            buildIncludeDirective(pagePathName,arg);
        }
    }

    private WikiPage findInheritedPage(String pageName) throws Exception{
        
        return pageCrawlerImpl.getInheritePage(pageName,testPage);
    }

    private String getPathNameForPage(WikiPage page) throws Exception {
        WikiPagePath pagePath = pageCrawler.getFullPath(page);
        return PathParser.render(pagePath);
    }

    private void buildIncludeDirective(String pagePathName, String arg) {
        newPageContent.append("\n!include ").append(arg).append(" .").append(pagePathName).append("\n");
    }

}
