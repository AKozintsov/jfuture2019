package com.anton.moviesystem.search;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchService {

    private final DocumentService documentService;

    @Value("${google.search.url}")
    private String googleSearchUrl;

    String getDirector(String movieName) {
        String movieDetailsHref = getMovieDetailsHref(movieName);
        Document document = documentService.getDocumentByUrl(movieDetailsHref);
        Element table = document.selectFirst("table.infobox");
        String directorName = table.getElementsByTag("tr").stream()
                .filter(tr -> tr.select("th").text().equals("Directed by"))
                .findFirst()
                .map(tr -> tr.select("a[href]").text())
                .orElse("Not Found");
        log.info("Director was found - " + directorName);
        return directorName;
    }

    private String getMovieDetailsHref(String searchValue) {
        String searchURL = googleSearchUrl + searchValue + " movie wikipedia";
        Document document = documentService.getDocumentByUrl(searchURL);
        Elements results = document.select("a[href]");
        return results.stream()
                .filter(element -> element.attr("href").contains("wikipedia.org/"))
                .findFirst()
                .map(element -> documentService.parseUrl(element.attr("href")))
                .orElse("Not Found");
    }
}
