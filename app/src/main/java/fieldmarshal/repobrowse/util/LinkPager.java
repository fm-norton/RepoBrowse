package fieldmarshal.repobrowse.util;

import android.support.annotation.NonNull;
import okhttp3.Headers;

/**
 * Created by fieldmarshal on 15.11.17.
 */

public class LinkPager {
    private static final String DELIM_LINKS = ",";

    private static final String DELIM_LINK_PARAM = ";";
    private static final String HEADER_LINK = "Link";
    private static final String META_REL = "rel";
    private static final String META_FIRST = "first";
    private static final String META_LAST = "last";
    private static final String META_NEXT = "next";
    private static final String META_PREV = "prev";
    private static final String HEADER_LAST = "X-Last";
    private static final String HEADER_NEXT = "X-Next";

    private String first;
    private String last;
    private String next;
    private String prev;


    /**
     * Parse links from executed method
     */
    public LinkPager(Headers headers) {
        String linkHeader = headers.get(HEADER_LINK);
        if (linkHeader != null) {
            String[] links = linkHeader.split(DELIM_LINKS);
            for (String link : links) {
                String[] segments = link.split(DELIM_LINK_PARAM);
                if (segments.length < 2)
                    continue;

                String linkPart = segments[0].trim();
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                    continue;
                linkPart = linkPart.substring(1, linkPart.length() - 1);

                for (int i = 1; i < segments.length; i++) {
                    String[] rel = segments[i].trim().split("=");
                    if (rel.length < 2 || !META_REL.equals(rel[0]))
                        continue;

                    String relValue = rel[1];
                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                        relValue = relValue.substring(1, relValue.length() - 1);

                    if (META_FIRST.equals(relValue))
                        first = linkPart;
                    else if (META_LAST.equals(relValue))
                        last = linkPart;
                    else if (META_NEXT.equals(relValue))
                        next = linkPart;
                    else if (META_PREV.equals(relValue))
                        prev = linkPart;
                }
            }
        } else {
            next = headers.get(HEADER_NEXT);
            last = headers.get(HEADER_LAST);
        }
    }

    // Better when links are non-null
    public @NonNull String getFirst() {
        return first;
    }

    public @NonNull String getLast() {
        return last;
    }

    public @NonNull String getNext() {
        return next;
    }

    public Integer getPage(String url) {
        final String[] trimmed = url.split("page=");
        return Integer.getInteger(trimmed[1]);
    }

    public String getPrev() {
        return prev;
    }
}
