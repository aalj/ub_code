package net.lll0.bus.database;

import android.text.TextUtils;


import net.lll0.bus.database.bean.LineInfoBean;
import net.lll0.bus.database.bean.SearchLineBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/1/1.
 */
public class ParsesHomeData {
    private Document doc = null;


    public ParsesHomeData(String html) {
        doc = Jsoup.parse(html);
    }

    public List<SearchLineBean> parseHtmlSearchLine() {

        List<SearchLineBean> lineBeans = new ArrayList<>();

        Elements spanDiv = doc.select("span").select("table");
        if (spanDiv.size() <= 0) {
            SearchLineBean lineBean = new SearchLineBean();
            lineBean.pathName = spanDiv.text();
            lineBean.lineName = "";
            lineBean.link = "";
            lineBeans.add(lineBean);
            return lineBeans;
        }

        Elements trs = spanDiv.select("tr");


        SearchLineBean lineBean = null;
        for (int i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            if (lineBean == null) {
                lineBean = new SearchLineBean();
            }
            for (int j = 0; j < tds.size(); j++) {
                Elements a = tds.get(j).select("a");
                String href = a.attr("href");
                String divName = tds.get(j).text();
                if (TextUtils.isEmpty(href)) {
                    lineBean.pathName = divName;
                } else {
                    lineBean.lineName = divName;
                    lineBean.link = href;
                }
            }
            if (lineBean != null && lineBean.link != null) {
                lineBeans.add(lineBean);
            }
            lineBean = null;
        }
        return lineBeans;

    }

    public List<SearchLineBean> parseHtmlSearchLineV2() {

        List<SearchLineBean> lineBeans = new ArrayList<>();

        Elements dl = doc.select("dl");
        for (int i = 0; i < dl.size(); i++) {
            Elements a = dl.get(i).select("a");
            SearchLineBean lineBean = new SearchLineBean();

            for (Element element : a) {
                if ("icomoon star1".equals(element.attr("class"))) {
                    String startLineId = element.attr("lineID");
                    String endLineId = element.attr("roLine");
                    lineBean.startLineID = startLineId;
                    lineBean.endLineID = endLineId;
                }
                if ("istationList fix".equals(element.attr("class"))) {
                    lineBean.link = element.attr("href");

                    String b = element.select("b").text();
                    Elements p1 = element.select("p");
                    String p = "";
                    if (p1.size() > 1) {
                        p = p1.get(1).text();
                    }
                    if (TextUtils.isEmpty(b) && TextUtils.isEmpty(p)) {
                        continue;
                    }
                    lineBean.pathName = p;
                    lineBean.lineName = b;

                }
            }
            lineBeans.add(lineBean);
        }
        return lineBeans;

    }


    public List<LineInfoBean> parseHtmlLineInfo() {
        List<LineInfoBean> lineBeans = new ArrayList<>();
        Elements spanDiv = doc.select("span").select("table");


        Elements trs = spanDiv.select("tr");


        LineInfoBean lineBean = null;
        for (int i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            if (lineBean == null) {
                lineBean = new LineInfoBean();
            }
            int size = tds.size();
            if (size > 0) {
                for (int j = 0; j < size; j++) {
                    Elements a = tds.get(j).select("a");
                    String divName = tds.get(j).text();
                    switch (j) {
                        case 0:
                            String href = a.attr("href");
                            lineBean.stationName = divName;
                            lineBean.lineUrl = href;
                            break;
                        case 1:
                            lineBean.stationNum = divName;

                            break;
                        case 2:
                            lineBean.carNumber = divName;
                            break;
                        case 3:
                            lineBean.time = divName;
                            break;
                    }

                }
                lineBeans.add(lineBean);
                lineBean = null;
            }
        }
        return lineBeans;
    }

    public List<LineInfoBean> parseHtmlLineInfoV2() {
        List<LineInfoBean> lineBeans = new ArrayList<>();
        Elements classDiv = doc.getElementsByClass("ldItem");

        for (int i = 0; i < classDiv.size(); i++) {
            Element element = classDiv.get(i);
            LineInfoBean lineBean = null;
            if ("ldItem fix".equals(element.attr("class"))) {
                if (lineBean == null) {
                    lineBean = new LineInfoBean();
                }
                String id = element.attr("id");
                lineBean.stationId = id;
                Elements b = element.select("b");
                if (b.size() > 0) {
                    lineBean.index = b.get(0).text();
                }
                Elements a = element.select("a");
                if (a.size() == 0 && b.size() == 3) {
                    lineBean.stationName = b.get(2).text();
                    lineBeans.add(lineBean);
                    continue;
                }
                String href = a.attr("href");
                String text = a.text();
                if (TextUtils.isEmpty(href) && TextUtils.isEmpty(text)) {
                    continue;
                }
                lineBean.lineUrl = href;
                lineBean.stationName = text;
                lineBeans.add(lineBean);
            }
        }
        return lineBeans;
    }





}
