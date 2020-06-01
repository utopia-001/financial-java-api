package io.github.mariazevedo88.financialjavaapi.util.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

/**
 * Class that implements the necessary settings for the API to works.
 *  
 * @author Mariana Azevedo
 * @since 02/04/2020
 */
@Configuration
public class FinancialApiConfiguration {
	
	/**
	 * Method that allow discovering links by relation type from some source.
	 * 
	 * @author Mariana Azevedo
	 * @since 02/04/2020
	 * 
	 * @return <code>LinkDiscoverers</code> object
	 */
	@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }
}
