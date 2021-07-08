package ca.navid.a2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Singleton object used to contain immediate cache need (JVM scope)
 * To be complete by students for caching layer
 */
public class Singleton {

    //Logger instance to be used if you need to print out any debug messages.
    private static final Logger logger = LoggerFactory.getLogger(Singleton.class);

    //only one constructor ... private default constructor ... why?: avoid public instantiation
    private Singleton() { }

    public final static int MAX_CACHE_SIZE = 500000;

    //static instance of the class
    private static volatile Singleton instance = null;

    //underlying map object
    private static Map<UUID, MultipleListingService> map;
    private static Map<UUID, MultipleListingService> cache;


    /**
     * getInstance to get a reference to the singleton class
     * @return the Singleton that allows caching the MLS records
     */
    public static Singleton getInstance()
    {
        //if instance is created, no need to creat a new one
        if (null == instance)
        {
            //double clutching to assure no issues with other threads
            synchronized (Singleton.class)
            {
                if (null == instance)
                {
                    instance = new Singleton();
                    map = new LinkedHashMap<>() {
                        @Override
                        protected boolean removeEldestEntry(final Map.Entry old) {
                            return size() > MAX_CACHE_SIZE;
                        }
                    };
                    cache = Collections.synchronizedMap(map);
                }
            }
        }
        return instance;
    }

    /**
     * Put a MLS record in the cache
     * @param id UUID of the MLS record
     * @param record the actual MLS record
     * @param forceUpdate if true, perform a force update
     */
    public void cachePut(UUID id, MultipleListingService record, boolean forceUpdate)
    {
        if (!forceUpdate && cache.containsKey(id)) return;
        cache.put(id, record);
    }

    /**
     * Cache lookup and get operation
     * @param id UUID of the record to be looked up and fetched.
     * @return the actual MLS record and null of not found in cache.
     */
    public MultipleListingService cacheGet(UUID id)
    {
        MultipleListingService record = cache.getOrDefault(id, null);
        if (record != null) logger.debug("Cache hit for " + record.getId());
        return record;
    }
}
