package customer.models;

import org.springframework.data.annotation.AccessType;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public abstract class PagingResourceSupport extends ResourceSupport {
    @AccessType(AccessType.Type.PROPERTY)
    public void setLinks(List<Link> links) {
        List<Link> actual = super.getLinks();
        actual.clear();
        actual.addAll(links);
    }
}
