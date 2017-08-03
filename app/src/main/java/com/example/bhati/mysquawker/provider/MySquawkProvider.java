package com.example.bhati.mysquawker.provider;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by Bhati on 8/1/2017.
 */

@ContentProvider(authority = MySquawkProvider.AUTHORITY, database = MySquawkDatabase.class)
public final class MySquawkProvider {

    public static final String AUTHORITY = "com.example.bhati.mysquawker.provider.provider";

    @TableEndpoint(table = MySquawkDatabase.SQUAWK_MESSAGES)
    public static class SquawkMessages {

        @ContentUri(
                path = "messages",
                type = "vnd.android.cursor.dir/messages",
                defaultSort = MySquawkContract.COLUMN_DATE + " DESC")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/messages");
    }
}
