package com.thondigital.nc.data.remote

import com.thondigital.nc.domain.models.EventDetailsResponse
import com.thondigital.nc.domain.repository.EventRepository

class FakeEventRepository : EventRepository {
    override suspend fun getEventById(eventId: Long): EventDetailsResponse {
        return EventDetailsResponse(
            id = 1,
            title = "Congresso de jovens 2023",
            date = "22/11/2023",
            description = "O Congresso de Jovens é mais do que um encontro, é uma experiência de crescimento espiritual, troca de ideias e fortalecimento de laços fraternos. Teremos palestras inspiradoras, workshops práticos, momentos de louvor e adoração, além de diversas atividades que visam promover o entendimento e aprofundamento na fé cristã.\n" +
                          "\n" +
                          "Junte-se a nós para um dia repleto de energias positivas, mensagens transformadoras e conexões significativas. Este é o momento de fortalecermos a nossa fé, compartilharmos experiências e nos unirmos como uma comunidade dedicada aos valores cristãos.\n" +
                          "\n" +
                          "Sua presença é fundamental para tornar este Congresso ainda mais especial. Esperamos contar com a sua participação e contribuição para que juntos possamos vivenciar um dia inesquecível de reflexão e celebração.\n" +
                          "\n" +
                          "Para confirmar sua presença e obter mais informações, visite [site do Ministério] ou entre em contato conosco pelos meios disponíveis.\n" +
                          "\n" +
                          "Contamos com a sua presença para fazer do Congresso de Jovens 2023 um evento marcante em nossas vidas.\n" +
                          "\n" +
                          "Que a paz e a graça estejam convosco.\n" +
                          "\n",
            image = "https://andremartinsoficial.com/wp-content/uploads/2021/04/o-motivo-do-nosso-louvor-andre-martins.jpg",
            location = "Sede - Ministério Nação da Cruz, Rua dos aventais, Nº 5000, Macapá - Ap",
        )
    }
}