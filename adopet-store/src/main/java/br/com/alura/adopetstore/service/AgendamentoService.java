package br.com.alura.adopetstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    @Autowired
    private RelatorioService relatorioService;

    /*
    * O cron é semelhante a uma expressão regular, na qual passamos seis campos relacionados ao tempo, nessa ordem:
    * segundo, minuto, hora, dia, mês e ano.
    * Se configurássemos, por exemplo, duas horas e qualquer segundo ("* 0 2 * *"), ele enviaria um e-mail a cada
    * segundo dentro dessa hora do dia.
    * Por isso, é importante que nos atentemos bastante ao que escrevemos nessa expressão.
     */
    //
    @Scheduled(cron = "0 54 17 * * *")
    public void envioEmailAgendamento(){
        var estoqueZerado = relatorioService.infoEstoque();
        var faturamentoObtido = relatorioService.faturamentoObtido();

        System.out.println("Estoque Zerado: " + estoqueZerado);
        System.out.println("Faturamento Obtido: " + faturamentoObtido);
    }
}
