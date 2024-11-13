package org.example.shortenerApi.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.example.shortenerApi.config.JpaEntityManager;
import org.example.shortenerApi.interceptor.EntityManagerInterceptorBinding;
import org.example.shortenerApi.model.entity.MappedUrl;
import org.example.shortenerApi.model.response.UrlResponse;
import org.example.shortenerApi.utils.Shorter;

import java.util.List;

@ApplicationScoped
@EntityManagerInterceptorBinding
public class ShortenerService implements IShortenerService {

    @Inject
    @JpaEntityManager
    private EntityManager entityManager;

    @Override
    public UrlResponse getShortLink(final String url) {
        UrlResponse urlResponse;
        MappedUrl existentMappedUrl = findMappedUrlByLongUrl(url);
        if (existentMappedUrl != null) {
            urlResponse = UrlResponse.builder().url(existentMappedUrl.getShortUrl()).build();
        } else {
            urlResponse = UrlResponse.builder().url(generateNewMappedUrl(url).getShortUrl()).build();
        }
        return urlResponse;
    }

    private MappedUrl generateNewMappedUrl(final String longUrl) {
        MappedUrl mappedUrl = new MappedUrl();
        mappedUrl.setShortUrl(Shorter.generate());
        mappedUrl.setLongUrl(longUrl);
        entityManager.persist(mappedUrl);
        return mappedUrl;
    }

    private MappedUrl findMappedUrlByLongUrl(final String longUrl) {
        List<MappedUrl> coincidences = entityManager
                .createQuery("SELECT m FROM MappedUrl m WHERE m.longUrl = :longUrl ", MappedUrl.class)
                .setParameter("longUrl", longUrl)
                .getResultList();


        return coincidences.isEmpty()? null : coincidences.get(0);
    }

    @Override
    public UrlResponse getLongUrl(String shortUrl) {

        MappedUrl mappedUrl = entityManager
                .createQuery("SELECT m FROM MappedUrl m WHERE m.shortUrl = :shortUrl", MappedUrl.class)
                .setParameter("shortUrl", shortUrl)
                .getSingleResult();

        return UrlResponse.builder().url(mappedUrl.getLongUrl()).build();

    }


}
