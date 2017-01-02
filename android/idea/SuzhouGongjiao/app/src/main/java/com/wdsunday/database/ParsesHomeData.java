package com.wdsunday.database;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Log;
import com.wdsunday.contstant.BaseConsTent;
import com.wdsunday.database.bean.LineBean;
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
    private String html = null;


    public ParsesHomeData(String html) {
        this.html = html;
    }

    public List<LineBean> parseHtml() {
        Document doc = Jsoup.parse(html);
        Elements trs = doc.select("span").select("table").select("tr");
        List<LineBean> lineBeans = new ArrayList<>();
        LineBean lineBean = null;
        for (int i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            if (lineBean == null) {
                lineBean = new LineBean();
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


}
