package ca.navid.a2;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * A utility class for general util and helper functions in A2
 */
public class A2Utils
{
    /**
     * Splitter/Delimiter used in the MLS record text file (e.g., our dummy database)
     */
    public static final Splitter splitter = Splitter.on(" %% ");

    //Logger instance to be used if you need to print out any debug messages.
    private static final Logger logger = LoggerFactory.getLogger(A2Utils.class);

    /**
     * Helps to parse a given text line in the mls.txt file and to extract the
     * tokens and construct the corresponding MultipleListingService record.
     * @param text a given text line in the format of mls.txt
     *             (refer to resources/data/mls.txt)
     * @return the MLS record using the tokens in the given text string.
     */
    public static MultipleListingService createMLSFromTextRecord(String text)
    {
        //a text line may look like:
        // 03b9cbea-a90d-406c-9e5b-623da6e2736d %% 2 Yonge Street %% 1000002
        // 3 tokens: UUID, Address and the Price of the property
        MultipleListingService mls = null;
        List<String> tokens = Lists.newArrayList();

        for(String str : splitter.split(text))
            tokens.add(str);

        logger.debug("found " + tokens.size() + "tokens in: " + text );
        if(tokens.size() == 3)
        {
            mls = new MultipleListingService.Builder(UUID.fromString(tokens.get(0)))
                    .locatedAt(tokens.get(1))
                    .pricedAt(Integer.valueOf(tokens.get(2)))
                    .build();
        }
        return mls;
    }
}
