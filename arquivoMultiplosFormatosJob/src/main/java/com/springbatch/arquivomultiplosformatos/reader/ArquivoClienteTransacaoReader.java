package com.springbatch.arquivomultiplosformatos.reader;

import com.springbatch.arquivomultiplosformatos.dominio.Cliente;
import com.springbatch.arquivomultiplosformatos.dominio.Transacao;
import org.springframework.batch.item.*;

public class ArquivoClienteTransacaoReader implements ItemStreamReader<Cliente> {

    private Object objAtual;
    private ItemStreamReader<Object> delegate;

    public ArquivoClienteTransacaoReader(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    //Método para leitura de clientes e adicionar as transações seguintes aos mesmos
    //
    // 0,João,Silva,32,joao@test.com
    // 1,t1c1,Cadeado,50.80
    // 1,t2c1,TV,1500

    // 0,Maria,Silva,30,maria@test.com
    // 1,t1c2,Geladeira duplex,2000
    // 1,t2c2,Mesa,4500
    // 1,t3c2,Sofá,1099,99
    @Override
    public Cliente read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(objAtual == null)
            objAtual = delegate.read(); //ler objeto

        Cliente cliente = (Cliente) objAtual;
        objAtual = null;

        //Adicionar todos os registros seguintes(transações) no cliente, ao terminar retornar o cliente
        if(cliente != null) {
            while (peek() instanceof Transacao)
                cliente.getTransacaos().add((Transacao) objAtual);
        }
        return cliente;
    }

    //Verifica se é transação ou cliente no while
    private Object peek() throws Exception {
        objAtual = delegate.read(); //leitura do próximo item
        return objAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }
}
