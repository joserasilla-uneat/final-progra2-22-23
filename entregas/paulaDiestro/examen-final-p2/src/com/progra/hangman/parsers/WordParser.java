package com.progra.hangman.parsers;

import com.progra.hangman.base.LargeWord;
import com.progra.hangman.base.MediumWord;
import com.progra.hangman.base.ShortWord;
import com.progra.hangman.base.Word;
import com.progra.hangman.exceptions.InvalidIdException;
import com.progra.hangman.exceptions.InvalidWordException;

public class WordParser implements Parser {


    String regex; // Cadena de texto que representa el patrón de expresión regular
    final int ELEMENT_COUNT = 3; // Número de elementos que debe contener la cadena de texto al hacer split

    public WordParser() {
        // Regex por defecto, separa por comas "," la cadena de texto que se le pasa al método parse
        this.regex = ",";
    }

    public WordParser(String regex) {
        this.regex = regex;
    }

    public Word parse(String tokens) throws InvalidWordException, InvalidIdException {

        String[] words = tokens.split(this.regex);
        sizeValidator(words);
        int id = idValidator(words[0]);
        String word = words[1];
        String type = words[2];
        Word wordObj = null;
        switch(type){
            case "LARGE":
                wordObj = new LargeWord(id, word);
                break;
            case "MEDIUM":
                wordObj = new MediumWord(id, word);
                break;
            case "SHORT":
                wordObj = new ShortWord(id, word);
                break;
            default:
                throw new InvalidWordException("Tipo de palabra inválido");
        }
        /*
        * Tokens es una cadena que contiene la información de una palabra.
        * Se encarga de validar que la cadena contenga 3 elementos separados por el caracter regex caso contrario lanza una excepción InvalidWordException
        * La cadena debe tener la forma:
        * codigo,palabra,tipo
        * codigo: es un número que identifica la palabra
        * palabra: es la palabra en sí
        * tipo: es el tipo de palabra que es: LARGA, MEDIA o CORTA
        * Dependiendo del tipo crear la instancia de la clase Word correspondiente, que puede ser de la clase WordShort, WordMedium o WordLong
        * */

        return wordObj;
    }

    @Override
    public Word parse(String tokens, int id) throws InvalidWordException, InvalidIdException {
        return null;
    }

    /*
     Parsea el Id de la palabra si ocurre un error lanza una excepción InvalidIdException
    */
    private int idValidator(String id) throws InvalidIdException {
        int idInt;

        try{
            idInt = Integer.parseInt(id);
        }catch(Exception e){
            throw new InvalidIdException(e.getMessage());
        }

        return idInt;
    }

    /*
    * Valida que la cadena contenga 3 elementos separados por el caracter regex caso contrario lanza una excepción InvalidWordException
     */
    private void sizeValidator(String[] words) throws InvalidWordException {
        if(words.length != this.ELEMENT_COUNT){
            throw new InvalidWordException("Valores de la palabra faltantes");
        }
    }


}
