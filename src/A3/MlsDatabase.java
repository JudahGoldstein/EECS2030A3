package A3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The class that holds all the MLS records
 *
 */

public class MlsDatabase {
    private static volatile MlsDatabase instance = null;
    public static String CollectionDisclaimer;

    // the actual database component is a Map
    private static volatile Map<UUID, MlsRecord> cache = null;

    // a maximum cache size needs to be declared - as noted from the Test 1 solution for Q4B
    // set an arbitrary max size of 100
    // must be publicly accessible, final (can't be modified), and static (belongs to class)
    public final static int MAX_CACHE_SIZE = 100;

    /**
     * getInstance - obtain the reference to the single instance of the Singleton class
     * @return the single instance of the Singleton object
     */
    public static MlsDatabase getInstance() {
        // check that there is no instance of the class created yet
        if (null == instance) {
            // synchronize thread access to shared data - ensure that only one instance is made
            synchronized (MlsDatabase.class) {
                // double check that there is still no instance created yet
                if (null == instance) {
                    // create the instance and also initialize the cache as a HashMap
                    instance = new MlsDatabase();
                    cache = new HashMap<UUID, MlsRecord>();
                }
            }
        }

        return instance;
    }

    public static String getCollectionDisclaimer() {
        return CollectionDisclaimer;
    }

    public static void setCollectionDisclaimer(String CollectionDisclaimer) {
        MlsDatabase.CollectionDisclaimer = CollectionDisclaimer;
    }

    public MlsRecord searchDatabase(UUID uuid) {
        return MlsDatabase.cache.get(uuid);
    }

    /**
     * Add a new entry or modify an existing entry in the cache, using a given key and value
     *
     * @param uuid    The UUID that will be the key for the entry
     * @param listing The MultipleListingService that will be the value for the entry
     * @return        A boolean to inform the program whether the value was added or not
     */
    public boolean addListing(UUID uuid, MlsRecord listing) {
        if (MlsDatabase.cache.size() < MlsDatabase.MAX_CACHE_SIZE) {
            MlsDatabase.cache.put(uuid, listing);
            return true; // if added or modified
        }
        return false; // if not added due to max cache size reached
    }

    public MlsRecord deleteListing(UUID uuid) {
        if (searchDatabase(uuid) != null) {
            MlsRecord deletedListing = MlsDatabase.cache.remove(uuid);
            return deletedListing; // if deleted
        }
        return null; // if nothing deleted
    }
}
