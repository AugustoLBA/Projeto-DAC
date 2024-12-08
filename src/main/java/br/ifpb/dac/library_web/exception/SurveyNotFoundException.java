package br.ifpb.dac.library_web.exception;

/**
 * Essa classe representa os erros perosnalizados de contratos
 */
public class SurveyNotFoundException extends RuntimeException{

    /**
     * O metodo espera uma mensagem personalizada para ser adicionada ao construtor
     * @param message;
     */
    public SurveyNotFoundException(String message){
        super(message);
    }
}
