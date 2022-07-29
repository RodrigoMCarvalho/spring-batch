package com.springbatch.processadorclassifier.processor;

import com.springbatch.processadorclassifier.dominio.Transacao;
import org.springframework.batch.item.ItemProcessor;

                                                        //itemLido, itemRetornado
public class TransacaoProcessor implements ItemProcessor<Transacao, Transacao> {

    @Override
    public Transacao process(Transacao transacao) {
        System.out.println(String.format("\nAplicando regras de negócio na transacao %s", transacao.getId()));
        return transacao;
    }
}
