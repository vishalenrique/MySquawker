package com.example.bhati.mysquawker.provider;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Bhati on 8/1/2017.
 */

@Database(version =MySquawkDatabase.VERSION )
public class MySquawkDatabase {
    public static final int VERSION=1;

    @Table(MySquawkContract.class)
    public static final String SQUAWK_MESSAGES="squawk_messages";
}
