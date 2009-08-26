package database;

import database.generator.RandomNameGenerator;
import database.generator.RandomLastNameGenerator;
import database.generator.RandomMiddleNameGenerator;
import database.generator.RandomAddressGenerator;

import java.util.HashMap;

import dbentities.Sex;

/**
 * Created by IntelliJ IDEA.
 * User: nastya
 * Date: 21.08.2009
 * Time: 1:24:29
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseConstants {
    public static final int DEFAULT_NAME_LENGTH = RandomNameGenerator.maxLength();
    public static final int DEFAULT_LAST_NAME_LENGTH = RandomLastNameGenerator.maxLength();
    public static final int DEFAULT_MIDDLE_NAME_LENGTH = RandomMiddleNameGenerator.maxLength();
    public static final int DEFAULT_ADDRESS_LENGTH = RandomAddressGenerator.maxLength();
    public static final int DEFAULT_SEX_LENGTH = 1;
    public static final int DEFAULT_PHONE_LENGTH = 10;
    public static final String SPACE = " ";
    private static final String DEFAULT_DB_SEX_MALE_VALUE = "0";
    private static final String DEFAULT_DB_SEX_FEMALE_VALUE = "1";
    private static final String DEFAULT_DB_SEX_UNKNOWN_VALUE = "2";

    public static String getIdBySex(Sex sex){
        switch (sex) {
            case FEMALE:
                return DatabaseConstants.DEFAULT_DB_SEX_FEMALE_VALUE;
            case MALE:
                return DatabaseConstants.DEFAULT_DB_SEX_MALE_VALUE;
            case UNKNOWN:
                return DatabaseConstants.DEFAULT_DB_SEX_UNKNOWN_VALUE;
        }
        return DatabaseConstants.DEFAULT_DB_SEX_UNKNOWN_VALUE;
    }

    public static Sex getSexById(String id){
        if(id == null) return Sex.UNKNOWN;
        if(id.equals(DEFAULT_DB_SEX_FEMALE_VALUE)){
            return Sex.FEMALE;
        }else if(id.equals(DEFAULT_DB_SEX_MALE_VALUE)){
            return Sex.MALE;
        }else{
            return Sex.UNKNOWN;
        }
    }
}
