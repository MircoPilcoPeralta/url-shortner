package org.example.shortenerApi;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.shortenerApi.model.request.GenerateShortUrlRequest;
import org.example.shortenerApi.model.response.UrlResponse;
import org.example.shortenerApi.service.IShortenerService;

@Path("/url")
public class ShortenerWs {

    @Inject
    private IShortenerService iShortenerService;

    @POST
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response generateShortUrl(GenerateShortUrlRequest request) {
        UrlResponse shortLink = iShortenerService.getShortLink(request.url());
        return  Response.ok(shortLink).build();
    }


}