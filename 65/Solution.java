import java.util.ArrayList;


interface ValidNumberValue {
    boolean doValidate(String s);
}


class Solution {
    private ValidNumber validNumber = new ValidNumber();
    public boolean isNumber(String s) {
       
            return validNumber.next(s);
    }
}
class ValidNumber {
    private ArrayList<ValidNumberValueTemplate> validators;
    private int pos = -1;

    public ValidNumber() {
        validators = new ArrayList<>();
        addValidator(new IntegerNumberValidator());
        addValidator(new FloatNumberValidator());
        addValidator(new HexNumberValidator());
        addValidator(new ScienceNumberValidator());
    }

    public void addValidator(ValidNumberValueTemplate validator) {
        validators.add(validator);
    }

    public boolean next(String s) {
        ++pos;
        if (pos >= validators.size()){
            pos = -1;
            return false;
        }
        if (validators.get(pos).doValidate(s)) {
            pos = -1;
            return true;
        } else {
            return next(s);
        }
    }

}

abstract class ValidNumberValueTemplate implements ValidNumberValue {
    public static boolean checkEmpty(String s) {
        if (s == null) {
            return false;
        }
        if ("".equals(s)) {
            return false;
        }
        return true;
    }

    public static String trimAndCheck(String s) {
        s = s.trim();
        if(s.startsWith("-")||s.startsWith("+")){
            s = s.substring(1);
        }

//        s = s.trim();
        if ("".equals(s)) {
            return null;
        }
        return s;
    }

    public boolean doValidate(String s) {
        if (!checkEmpty(s)) {
            return false;
        }
        s = trimAndCheck(s);
        if (s == null) {
            return false;
        }
        return validate(s);
    }

    abstract boolean validate(String s);
}

class IntegerNumberValidator extends ValidNumberValueTemplate {
    boolean validate(String s) {
        for (int i = s.length() - 1; i >= 0; --i) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

class FloatNumberValidator extends ValidNumberValueTemplate {
    boolean validate(String s) {
        if(!s.contains(".")) return false;
        int p = s.indexOf('.');
        ValidNumberValueTemplate integerNumberValidator = new IntegerNumberValidator();
        if(p > 0) {
            if(p != s.length() -1 ){
                String subStr1 = s.substring(0,p);
                String subStr2 = s.substring(p+1);
                if(subStr2.isEmpty()) return integerNumberValidator.validate(subStr1);
                if(!Character.isDigit(subStr1.charAt(0))
                        || !Character.isDigit(subStr2.charAt(0))) return false;
                if(subStr1.length()>1 && !Character.isDigit(subStr1.charAt(1))) return false;
                if(subStr2.length()>1 && !Character.isDigit(subStr2.charAt(1))) return false;
                return integerNumberValidator.validate(s.substring(0,p)) && integerNumberValidator.validate(s.substring(p+1));
            }else {
                return integerNumberValidator.validate(s.substring(0,p));
            }
        }else {
            String subStr = s.substring(p+1);
            if(subStr.isEmpty() || !Character.isDigit(subStr.charAt(0))) return false;
            return integerNumberValidator.validate(subStr);
        }
    }
}

class HexNumberValidator extends ValidNumberValueTemplate {
    boolean checkHexChar(char c) {
        return ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F') || Character.isDigit(c);
    }

    boolean validate(String s) {
        if (!(s.startsWith("0x") || s.startsWith("0X"))) return false;
        for (int i = s.length() - 1; i >= 2; --i) {
            if (!checkHexChar(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

class ScienceNumberValidator extends ValidNumberValueTemplate {


    boolean validate(String s) {
        if(!(s.contains("e")||s.contains("e"))) return false;
        int p = s.indexOf('e');
        if(p == -1) p =  s.indexOf('E');
        if((p == 0) || (p == s.length() - 1)) return false;
        //if(s.startsWith("0") && (s.charAt(1)!='e') && s.charAt(1)!='E') return false;
        ValidNumberValueTemplate floatNumberValidator = new FloatNumberValidator();
        ValidNumberValueTemplate integerNumberValidator = new IntegerNumberValidator();

        return (integerNumberValidator.validate(s.substring(0,p)) && integerNumberValidator.doValidate(s.substring(p+1)))
                ||(floatNumberValidator.validate(s.substring(0,p)) && integerNumberValidator.doValidate(s.substring(p+1)));
    }
}

