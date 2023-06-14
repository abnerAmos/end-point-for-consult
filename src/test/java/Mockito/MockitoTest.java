package Mockito;

import com.example.demo.enums.Status;
import com.example.demo.exception.ClientNotFoundException;
import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.request.RequestClient;
import com.example.demo.service.UpdateClientServiceImpl;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MockitoTest {

     /* Para utilizar @Mock as classes que implementam o repository (services) devem fazer a injeção via construtor
            com @Override não funciona*/
    @Mock
    private ClientRepository clientRepository;
    private UpdateClientServiceImpl updateClientService;

    @Captor
    private ArgumentCaptor<Client> captor; // Captura o Objeto e seus atributos.

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
        this.updateClientService = new UpdateClientServiceImpl(clientRepository);
        /* Método é chamado antes de cada teste para chamar o service,
        pois não é feito automáticamente após a conclusão do primeiro teste */
    }

    @Test
    void shouldUpdateClient() {
        Optional<Client> client = populateMock();

        Mockito
            .when(clientRepository.findById(1L))
            .thenReturn(client); // função que substitui a chamada no banco do método original

        Client updateClient = updateClientService.updateClientMock(1L,  updateMock());

        assertEquals("Abner Souza", updateClient.getName());

        Mockito.verify(clientRepository).save(updateClient);
    }

    @Test
    void shouldThrowException() {

        Mockito
                .when(clientRepository.findById(Mockito.anyLong()))
                .thenThrow(ClientNotFoundException.class); // forçando uma exception

        try {
            Client updateClient = updateClientService.updateClientMock(1L,  updateMock());
            Mockito.verifyNoInteractions(clientRepository.save(updateClient));
        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(ClientNotFoundException.class);
        }

    }

    @Test
    void shouldUpdateName() {
        Optional<Client> newClient = populateMock();

        Mockito
                .when(clientRepository.findByStatusAndId(Status.ATIVO, 1))
                .thenReturn(newClient); // função que substitui a chamada no banco do método original

        ResponseEntity<?> updateClient = updateClientService.updateName(1,  updateMock());

        Mockito.verify(clientRepository).save(captor.capture()); // Verifica se houve interação e captura o Objeto com seus dados.
        Client client = captor.getValue();

        assertEquals("Abner Souza", client.getName());
        assertEquals(200, updateClient.getStatusCode().value());
    }

    private RequestClient updateMock() { // método que simula um update
        RequestClient request = new RequestClient();
        request.setName("Abner Souza");
        request.setEmail("abner@email.com.br");
        request.setAge(30);

        return request;
    }

    private Optional<Client> populateMock() { // método que popula um mock
        Client client = new Client();
        client.setId(1L);
        client.setName("Abner Amos");
        client.setAge(29);
        client.setEmail("abner@email.com");

        return Optional.of(client);
    }

    // teste Básico de inicição ao Mockito --------------------------------------------------
    @Test
    void test() {
        ClientRepository mock = Mockito.mock(ClientRepository.class);
        List<Client> findAll = mock.findAll();
        assertTrue(findAll.isEmpty());
    }

}
