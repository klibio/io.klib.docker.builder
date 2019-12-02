package io.klib.aries.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.*;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

@Component(
	    property = {
	        JaxrsWhiteboardConstants.JAX_RS_APPLICATION_SELECT + "=(osgi.jaxrs.name=.default)",
	        JaxrsWhiteboardConstants.JAX_RS_RESOURCE + "=true"
	    },
	    service = ExampleResource.class
	)
@JaxrsResource
public class ExampleResource {

    @GET
    @Path("/env")  
    public String getEnv() {
//    	String result;
//    	result = System.getenv().keySet().stream().sorted().forEach(k->{tesdtsdt
//    		System.out.format("%30s = %s \n", k,System.getenv().get(k));
//    	});
//    	return result;
    	Map<String, String> env = System.getenv();
		String result = env.keySet().stream().sorted().map(k -> String.format("%30s = %s", k, env.get(k))).collect(Collectors.joining("<br>"));
		return result;
    }
    @GET
    @Path("/prop")
    public Properties getProp() {
		Properties props = System.getProperties();
		Set<Entry<Object, Object>> entrySet = props.entrySet();
		String result = entrySet.stream().sorted(new Comparator<Entry<Object, Object>>() {
			@Override
			public int compare(Entry<Object, Object> o1, Entry<Object, Object> o2) {
				o1.getKey().toString().compareTo(o2.getKey().toString());
				return 0;
			}
		}).map(e -> String.format("%30s = %s", e.getKey(), e.getValue())).collect(Collectors.joining("\n"));
		return props;
    }
}

//public String getEnv() {
//	String test;
//	List<String> key;
//	List<String> value;
//	key = System.getenv().keySet().stream().sorted().collect(Collectors.toList());
//	value = System.getenv().values().stream().sorted().collect(Collectors.toList());
//	List<String> newList = Stream.concat(key.stream(), value.stream()).collect(Collectors.toList());
//	test = String.join(",", newList);
//	return test;
//}