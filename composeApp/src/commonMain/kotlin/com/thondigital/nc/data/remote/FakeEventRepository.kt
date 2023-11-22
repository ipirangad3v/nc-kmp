package com.thondigital.nc.data.remote

import com.thondigital.nc.domain.models.EventDetailsResponse
import com.thondigital.nc.domain.repository.EventRepository

class FakeEventRepository : EventRepository {
    private val inMemoryDb = listOf(
        EventDetailsResponse(
            id = 1,
            title = "Congresso de jovens 2023",
            date = "22/11/2023",
            description = "Prezados Jovens,\n" +
                          "\n" +
                          "Com imensa alegria, estendemos a vocês o convite para o Congresso de Jovens 2023, organizado pelo Ministério Nação da Cruz. Este evento representa um dia especial de reflexão, aprendizado e comunhão, e será uma oportunidade única para enriquecermos nossa jornada espiritual.\n" +
                          "\n" +
                          "O Congresso de Jovens vai além de uma simples reunião; é um momento dedicado ao crescimento pessoal e à construção de laços sólidos entre os membros de nossa comunidade. Teremos palestras ministradas por líderes inspiradores, workshops práticos para aplicação dos ensinamentos no cotidiano e expressivas sessões de louvor e adoração.\n" +
                          "\n" +
                          "Ao participar deste evento, não apenas expandiremos nossos horizontes espirituais, mas também fortaleceremos nossa conexão uns com os outros. Haverá espaço para compartilhar experiências, fazer novas amizades e, acima de tudo, crescermos juntos como jovens dedicados à prática dos princípios cristãos.\n" +
                          "\n" +
                          "Sua presença é fundamental para tornar este Congresso memorável. A diversidade de perspectivas e a energia positiva que cada um de vocês traz consigo são essenciais para enriquecer nossas discussões e vivenciar a verdadeira essência do cristianismo.\n" +
                          "\n" +
                          "Para confirmar sua participação e acessar mais informações sobre o evento, visite [site do Ministério] ou entre em contato conosco pelos meios disponíveis.\n" +
                          "\n" +
                          "Esperamos ansiosos por este dia especial, onde, unidos em Cristo, poderemos aprender, compartilhar e celebrar a nossa fé.\n" +
                          "\n" +
                          "Que a paz e a graça estejam convosco.",
            image = "https://andremartinsoficial.com/wp-content/uploads/2021/04/o-motivo-do-nosso-louvor-andre-martins.jpg",
            location = "Sede do Ministério Nação da Cruz, Rua dos aventais, Nº 5000, Macapá - Ap",
            startingTime = "08:00",
            endingTime = "18:00",
        )
    )

    override suspend fun getEventById(eventId: Long): EventDetailsResponse {
        return inMemoryDb.find { it.id == eventId } ?: throw Exception("Event not found")
    }
}