package com.proyecto.utec_roomie.Roomie.controller;


import com.proyecto.utec_roomie.roomie.domain.Roomie;
import com.proyecto.utec_roomie.roomie.domain.RoomieService;
import com.proyecto.utec_roomie.roomie.dto.RoomieResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomieControllerTest.class)
public class RoomieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomieService roomieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRoomieByID() throws Exception {
        RoomieResponseDto roomieResponseDto = new RoomieResponseDto();


        when(roomieService.getRoomie(1L)).thenReturn(roomieResponseDto);

        mockMvc.perform(get("/roomie/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Adjust jsonPath as per the properties of roomieResponseDto
    }

    @Test
    public void testGetOwnRoomieInfo() throws Exception {
        Roomie roomie = new Roomie();


        when(roomieService.getRoomieOwnInfo()).thenReturn(roomie);

        mockMvc.perform(get("/roomie/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.property").value("value")); // Adjust jsonPath as per the properties of roomie
    }

    @Test
    public void testUpdateRoomie() throws Exception {
        RoomieResponseDto roomieResponseDto = new RoomieResponseDto();


        mockMvc.perform(patch("/roomie/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"property\": \"value\" }")) // Adjust the JSON as per the properties of roomieResponseDto
                .andExpect(status().isOk());

        verify(roomieService, times(1)).updateRoomie(any(RoomieResponseDto.class));
    }

    @Test
    public void testDeleteRoomie() throws Exception {
        doNothing().when(roomieService).deleteRoomie(1L);

        mockMvc.perform(delete("/roomie/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(roomieService, times(1)).deleteRoomie(1L);
    }
}
