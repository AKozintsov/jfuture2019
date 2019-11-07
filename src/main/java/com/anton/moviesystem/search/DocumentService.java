package com.anton.moviesystem.search;

import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
class DocumentService {

    @SneakyThrows
    Document getDocumentByUrl(String url) {
        Connection.Response response;
        Document document = null;
        response = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)").execute();
        if (response.statusCode() == HttpStatus.OK.value()) {
            document = response.parse();
        }
        return document;
    }

    String parseUrl(String url) {
        return url.substring(url.indexOf("=") + 1, url.indexOf("&"));
    }
}
