package africa.semicolon.phoneBook.utils;

import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;

public class CaseUtil {
    public static void sentenceCase(AddContactRequest request){
        request.setFirstName(convertCase(request.getFirstName()));
        request.setLastName(convertCase(request.getLastName()));
        request.setMiddleName(convertCase(request.getMiddleName()));
    }

    public static String ignoreCase(String name) {
        return convertCase(name);
    }

    private static String convertCase(String name) {
//        name = name.toLowerCase();
//        name = name.replace(name.charAt(0), Character.toUpperCase(name.charAt(0)));
        return Character.toUpperCase(name.charAt(0)) + (name.substring(1)).toLowerCase();
    }
}
