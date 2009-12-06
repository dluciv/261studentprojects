/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

/**
 *
 * @author Admin
 */
public enum ApplicationType {
    INNER, INDIVIDUAL, OPTIONAL, FREE, OTHERS;

    @Override
    public String toString() {
        switch(this){
            case INNER: return "Внутреннее";
            case INDIVIDUAL: return "Индивидуальное";
            case OPTIONAL: return "Опциональное";
            case FREE: return "Бесплатное";
            case OTHERS: return "";
            default: return "ОШИБКА";
        }
    }
}
