package code.page46;


/**
 * Created by qiong.liu on 2018/10/30.
 */
public class pageCrawlerImpl implements PageCrawler {
    WikiPage wikiPage;

    @Override
    public WikiPagePath getFullPath(WikiPage wikiPage) {
        return null;
    }

    public  static WikiPage getInheritePage(String pageName,WikiPage wikiPage){
        return wikiPage;
    }
}
