package com.br.luisvanique.academia.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.luisvanique.academia.domain.Endereco;
import com.br.luisvanique.academia.domain.aluno.Aluno;
import com.br.luisvanique.academia.domain.enums.StatusPagamento;
import com.br.luisvanique.academia.domain.instrutor.Instrutor;
import com.br.luisvanique.academia.domain.mensalidade.Mensalidade;
import com.br.luisvanique.academia.domain.mensalidade.TipoStatusMensalidade;
import com.br.luisvanique.academia.repository.AlunoRepository;
import com.br.luisvanique.academia.repository.InstrutorRepository;
import com.br.luisvanique.academia.repository.MensalidadeRepository;
import com.br.luisvanique.academia.repository.TipoStatusPagamentoRepository;

	

@Component
public class InstanciaDB implements CommandLineRunner {
	private InstrutorRepository instrutorRepository;
    private AlunoRepository alunoRepository;
    private MensalidadeRepository mensalidadeRepository;
    private TipoStatusPagamentoRepository tipoStatusPagamentoRepository;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    public InstanciaDB(AlunoRepository alunoRepository, MensalidadeRepository mensalidadeRepository,
    		InstrutorRepository instrutorRepository, TipoStatusPagamentoRepository tipoStatusPagamentoRepository) {
        this.alunoRepository = alunoRepository;
        this.mensalidadeRepository = mensalidadeRepository;
        this.instrutorRepository = instrutorRepository;
        this.tipoStatusPagamentoRepository = tipoStatusPagamentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    	Endereco endereco = new Endereco();
    	LocalDate dataAtual = LocalDate.now();
    	endereco.setLogradouro("Paulino pacheco de mello");
    	endereco.setBairro("Jardim Robru");
    	endereco.setCep("08150320");
    	endereco.setEstado("São Paulo");
    	endereco.setUf("SP");
    	
        Aluno aluno1 = new Aluno("Luis", endereco, "51914695852", "11952925758");
        alunoRepository.save(aluno1);
        
        Aluno aluno2 = new Aluno("Vanessa", endereco, "22502104890", "11985182653");
        alunoRepository.save(aluno2);

        Mensalidade mensalidade1 = new Mensalidade();
        mensalidade1.setAluno(aluno1);
        mensalidade1.setValor(100.00);
        mensalidade1.setStatus(StatusPagamento.PAGA.getCodigo());
        mensalidade1.setDataVencimento(dataAtual);
        mensalidadeRepository.save(mensalidade1);

        Mensalidade mensalidade2 = new Mensalidade();
        mensalidade2.setAluno(aluno2);
        mensalidade2.setValor(120.00);
        mensalidade2.setStatus(StatusPagamento.PENDENTE.getCodigo());
        mensalidade2.setDataVencimento(dataAtual);
        mensalidadeRepository.save(mensalidade2);
        
        List<TipoStatusMensalidade> status = new ArrayList<>();
        status.add(new TipoStatusMensalidade(StatusPagamento.VENCIDA.getCodigo(), StatusPagamento.VENCIDA.getDescricao()));
        status.add(new TipoStatusMensalidade(StatusPagamento.PENDENTE.getCodigo(), StatusPagamento.PENDENTE.getDescricao()));
        status.add(new TipoStatusMensalidade(StatusPagamento.PAGA.getCodigo(), StatusPagamento.PAGA.getDescricao()));
        
        tipoStatusPagamentoRepository.saveAll(status);
        
        
        Instrutor instrutor = new Instrutor("Sergio@gmail.com", encoder.encode("123"), "Sergio");
        instrutorRepository.save(instrutor);
    }
}
