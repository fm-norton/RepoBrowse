package fieldmarshal.repobrowse.util

import okhttp3.Headers

/**
 * Created by fieldmarshal on 15.11.17.
 */

class LinkPager
/**
 * Parses links for the executed method
 * @param headers headers to be parsed
 */
(headers: Headers) {

    var first: String? = null
        private set
    var last: String? = null
        private set
    var prev: String? = null
        private set
    var next: String? = null
        private set

    private var nextPage = 2

    init {
        val linkHeader = headers.get(HEADER_LINK)
        if (linkHeader != null) {
            val links = linkHeader.split(DELIM_LINKS.toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()
            for (link in links) {
                val segments = link.split(DELIM_LINK_PARAM.toRegex())
                        .dropLastWhile { it.isEmpty() }.toTypedArray()
                if (segments.size < 2)
                    continue

                var linkPart = segments[0].trim { it <= ' ' }
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                    continue
                linkPart = linkPart.substring(1, linkPart.length - 1)

                for (i in 1 until segments.size) {
                    val rel = segments[i].trim { it <= ' ' }.split("=".toRegex())
                            .dropLastWhile { it.isEmpty() }.toTypedArray()
                    if (rel.size < 2 || META_REL != rel[0])
                        continue

                    var relValue = rel[1]
                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                        relValue = relValue.substring(1, relValue.length - 1)

                    if (META_FIRST == relValue)
                        first = linkPart
                    else if (META_LAST == relValue)
                        last = linkPart
                    else if (META_NEXT == relValue)
                        next = linkPart
                    else if (META_PREV == relValue)
                        prev = linkPart
                }
            }
        } else {
            next = headers.get(HEADER_NEXT)
            last = headers.get(HEADER_LAST)
        }
    }

    fun getNextPage(): Int {
        val trimmed = next!!.split("page=".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray()
        nextPage = Integer.getInteger(trimmed[1])!!
        return nextPage
    }

    companion object {
        private val DELIM_LINKS = ","
        private val DELIM_LINK_PARAM = ";"

        private val HEADER_LINK = "Link"
        private val HEADER_LAST = "X-Last"
        private val HEADER_NEXT = "X-Next"
        private val META_REL = "rel"
        private val META_FIRST = "first"
        private val META_LAST = "last"
        private val META_PREV = "prev"
        private val META_NEXT = "next"
    }
}
