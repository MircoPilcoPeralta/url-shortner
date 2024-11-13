package org.example.shortenerApi.service;

import org.example.shortenerApi.model.response.UrlResponse;

public interface IShortenerService {
    UrlResponse getShortLink(String url);
    UrlResponse getLongUrl(String shortUrl);
}
