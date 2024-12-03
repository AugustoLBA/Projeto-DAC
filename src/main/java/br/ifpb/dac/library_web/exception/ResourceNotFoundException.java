package br.ifpb.dac.library_web.exception;

/**
 * Essa classe representa mensagens de erros do usuario
 */
public class ResourceNotFoundException extends RuntimeException{
    /**
     * O metodo espera uma mensagem personalizada para ser adicionada ao construtor
     * @param message
     */
    public ResourceNotFoundException(String message){
        super(message);
    }
}
