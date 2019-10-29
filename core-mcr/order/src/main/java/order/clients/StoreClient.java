package order.clients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
public class StoreClient {
    private static final Logger LOG = LoggerFactory.getLogger(StoreClient.class);

    public static class ProductPagedResources extends PagedResources<Product> {

    }

    private RestTemplate restTemplate;
    private String storeServiceHost;
    private long storeServicePort;

    @Autowired

    public StoreClient(
            @Value("${store.services.host:store}") String storeServiceHost,
            @Value("${store.services.port:8080}") long storeServicePort) {
        this.restTemplate = getRestTemplate();
        this.storeServiceHost = storeServiceHost;
        this.storeServicePort = storeServicePort;
    }

    protected RestTemplate getRestTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
        converter.setObjectMapper(mapper);

        return new RestTemplate(
                Collections.<HttpMessageConverter<?>>singletonList(converter));
    }

    public Collection<Product> findAll() {
        PagedResources<Product> pagedResources = restTemplate.getForObject(
                storeURL(), ProductPagedResources.class);
        return pagedResources.getContent();
    }

    private String storeURL() {
        String url = String.format("http://%s:%s/api/product/", storeServiceHost, storeServicePort);
        LOG.trace("Catalog: URL {} ", url);
        return url;
    }

    public Product getOne(ObjectId productId) {
        Product p = restTemplate.getForObject(storeURL() + productId, Product.class);
        return p;//restTemplate.getForObject(storeURL() + productId, Product.class);
    }

    public double getPrice(ObjectId productId) {
        return getOne(productId).getPrice();
    }
}
