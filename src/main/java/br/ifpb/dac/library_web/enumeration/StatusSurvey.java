package br.ifpb.dac.library_web.enumeration;

/**
 * Esse ENUM serve para qualificar uma vistoria.
 * Aproved indica que o livro em questao esta nas mesma condicões da época do emprestimo, e serra aceita a devoluçao.
 * Failed indica que o livro nao esta nas mesma condicões a época do emprestimo e sera aplicado as regras do contrato como multa e etc.
 */
public enum StatusSurvey {
    APROVED,
    FAILED;
}
