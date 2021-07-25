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
    public static final String CollectionDisclaimer = "All information held in the database may not reflect the most up-to-date information.";

    // the actual database component is a Map
    private static volatile Map<UUID, MlsRecord> database = null;

    // a maximum cache size needs to be declared - as noted from the Test 1 solution for Q4B
    // set an arbitrary max size of 100
    // must be publicly accessible, final (can't be modified), and static (belongs to class)
    public final static int MAX_STORAGE_SIZE = 100;

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
                    database = new HashMap<UUID, MlsRecord>();
                }
            }
        }

        return instance;
    }

    /**
     * Get the MLS Database collection disclaimer
     *
     * @return Collection Disclaimer
     */
    public static String getCollectionDisclaimer() {
        return CollectionDisclaimer;
    }

    /**
     * Find an MLS Record based on its UUID
     *
     * @param uuid the UUID to look up
     * @return the MLSRecord corresponding to that UUID
     */
    public MlsRecord searchDatabase(UUID uuid) {
        return MlsDatabase.database.get(uuid);
    }

    /**
     * Add a new entry, using a given key and value.
     *
     * @param uuid    The UUID that will be the key for the entry
     * @param listing The MlsRecord that will be the value for the entry
     * @return        A boolean to inform the program whether the value was added or not
     */
    public boolean addListing(UUID uuid, MlsRecord listing) {
        if (MlsDatabase.database.size() < MlsDatabase.MAX_STORAGE_SIZE) {
            MlsDatabase.database.put(uuid, listing);
            return true; // if added or modified
        }
        return false; // if not added due to max cache size reached
    }

    public MlsRecord deleteListing(UUID uuid) {
        if (searchDatabase(uuid) != null) {
            MlsRecord deletedListing = MlsDatabase.database.remove(uuid);
            return deletedListing; // if deleted
        }
        return null; // if nothing deleted
    }

    /**
     * Modify an existing entry, using a given key and value
     *
     * @param uuid    The UUID key to search for in the database
     * @param listing The MlsRecord that will be the new value for the entry
     * @return        A boolean to inform the program whether the value was modified or not
     */
    public boolean updateListing(UUID uuid, MlsRecord listing) {
        if (searchDatabase(uuid) != null) {
            MlsDatabase.database.put(uuid, listing);
            return true; // if updated
        }
        return false; // if not found, therefore no changes made
    }

    /**
     * Get the current size of database
     * @return the current size of database
     */
    public int getSize() {
        return MlsDatabase.database.size();
    }
}
