package online.reusing.springbootfun.property;

import com.mongodb.client.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class PropertyRepository {

    @Resource
    private MongoClient mongoClient;

    private MongoDatabase db;

    private String collectionName = "properties";

    private MongoCollection<Document> collection;

    @PostConstruct
    public void init() {
        db = mongoClient.getDatabase("test");
        collection = db.getCollection(collectionName);
    }


    public Collection<Property> findAll() {
        Collection<Property> properties = new ArrayList<>();
        FindIterable<Document> documents = collection.find();
        documents.iterator().forEachRemaining(d -> {
            Property prop = docToProperty(d);
            properties.add(prop);
        });

        return properties;
    }

    public Property findById(String id) {
        Document query = new Document("_id", new ObjectId(id));
        FindIterable<Document> documents = collection.find(query);

        Document doc = documents.first();
        if (doc != null) {
            return docToProperty(doc);
        }
        else { return null; }
    }

    public void insert(Property p) {
        Document doc = propertyToDocument(p);
        collection.insertOne(doc);
    }

    public void update(Property p) {
        Document doc = propertyToDocument(p);
        collection.updateOne(new Document("_id",new ObjectId(p.getId())), doc);
    }

    public void delete(String id) {
        Document d = new Document("_id",new ObjectId(id));
        collection.deleteOne(d);
    }

    private Property docToProperty(Document d) {
        Property prop = new Property();
        prop.setId(d.get("_id").toString());
        prop.setKey(d.getString("key"));
        prop.setValue(d.getString("value"));
        prop.setTarget(d.getString("target"));
        prop.setServer(d.getString("server"));
        prop.setEnv(d.getString("env"));
        prop.setDescription((d.getString("description")));

        return prop;
    }
    
    private Document propertyToDocument(Property p) {
        Document doc = new Document();
        doc.append("_id", new ObjectId(p.getId()));
        doc.append("key", p.getKey());
        doc.append("value", p.getValue());
        doc.append("target", p.getTarget());
        doc.append("server", p.getServer());
        doc.append("env", p.getEnv());
        doc.append("description", p.getDescription());
        
        return doc;
    }
}
