package ch.css.coaching.hearts.domain;

public class NoCardsAvailableException extends Exception {

    public NoCardsAvailableException(){
        super();
    }

    public NoCardsAvailableException(String message){
        super(message);
    }
}
