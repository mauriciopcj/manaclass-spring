package br.edu.jfpb.pweb2.manaclassspringboot.util.enums;

public enum SituacaoEnum {
    APROVADO("AP", "aprovado"),
    REPROVADO_FINAL("RP", "reprovado por final"),
    FINAL("FN", "Na final"),
    MATRICULADO("MT", "Matriculado"),
    REPROVADO_FALTA("RF", "Reprovado por falta");

    public String sigla;
    public String descricao;

    SituacaoEnum(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSigla() {
        return sigla;
    }
}
