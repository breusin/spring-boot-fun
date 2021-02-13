package online.reusing.springbootfun.property;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Service
public class PropertyService {

    @Resource
    private PropertyRepository propertyRepository;

    public Collection<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getProperty(String id) {
        return propertyRepository.findById(id);
    }

    public void addProperty(Property property) {
        propertyRepository.insert(property);
    }

    public void deleteProperty(String id) {
        propertyRepository.delete(id);
    }
}


