package com.epam.esm.exception.data;

public class ErrorMessage {
    public static final String ERROR_000001 = "tag is null";
    public static final String ERROR_000002 = "tag id should not be less than 0";
    public static final String ERROR_000003 = "tag name is null";
    public static final String ERROR_000004 = "tag name is empty";
    public static final String ERROR_000005 = "tag id should not be more than 30";
    public static final String ERROR_000007 = "tag with such id is not exist";
    public static final String ERROR_000008 = "tag with such name is not exist";

    public static final String ERROR_000020 = "certificate is not exist";
    public static final String ERROR_000021 = "certificate is empty";
    public static final String ERROR_000022 = "certificate id is less than 0";
    public static final String ERROR_000024 = "certificate name is null";
    public static final String ERROR_000025 = "certificate name is empty";
    public static final String ERROR_000026 = "certificate name is more than 30";
    public static final String ERROR_000027 = "certificate description is null";
    public static final String ERROR_000028 = "certificate description is empty";
    public static final String ERROR_000029 = "certificate description is more than 500";
    public static final String ERROR_000030 = "certificate description is less than 0";
    public static final String ERROR_000031 = "certificate date error";
    public static final String ERROR_000032 = "certificate date creation after today";
    public static final String ERROR_000033 = "certificate price to long";
    public static final String ERROR_000034 = "modification date after today";
    public static final String ERROR_000035 = "modification date earlier than today";
    public static final String ERROR_000036 = "duration less than 1";
    public static final String ERROR_000038 = "duration more than 365";

    private ErrorMessage() {
    }
}