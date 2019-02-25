package code.page46;

/**
 * Created by qiong.liu on 2018/10/30.
 */
public interface PageCrawler {
     WikiPagePath wikiPagePath=new WikiPagePath();
    public WikiPagePath getFullPath(WikiPage wikiPage) ;
}
