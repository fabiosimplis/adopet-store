package br.com.alura.adopetstore.service;

import br.com.alura.adopetstore.email.EmailRelatorioGerado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AgendamentoService {

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private EmailRelatorioGerado enviador;

    /*
    * O cron é semelhante a uma expressão regular, na qual passamos seis campos relacionados ao tempo, nessa ordem:
    * segundo, minuto, hora, dia, mês e ano.
    * Se configurássemos, por exemplo, duas horas e qualquer segundo ("* 0 2 * *"), ele enviaria um e-mail a cada
    * segundo dentro dessa hora do dia.
    * Por isso, é importante que nos atentemos bastante ao que escrevemos nessa expressão.
     */
    //
    @Scheduled(cron = "0 27 20 * * *")
    public void envioEmailAgendamento(){
        var estoqueZerado = relatorioService.infoEstoque();
        var faturamentoObtido = relatorioService.faturamentoObtido();

        //Sincronizando as threads com a thread corrente main
        CompletableFuture.allOf(estoqueZerado, faturamentoObtido).join();

        try {
            enviador.enviar(estoqueZerado.get(), faturamentoObtido.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("Estoque Zerado: " + estoqueZerado);
//        System.out.println("Faturamento Obtido: " + faturamentoObtido);
        System.out.println("Thread Agendamento: "+ Thread.currentThread().getName());

    }
}
