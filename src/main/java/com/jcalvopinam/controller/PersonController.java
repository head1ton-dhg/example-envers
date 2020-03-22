/*
 * MIT License
 *
 * Copyright (c) 2017 JUAN CALVOPINA M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

/**
 * Endpoint for access to the services
 */
package com.jcalvopinam.controller;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonRequestDTO;
import com.jcalvopinam.dto.PersonResponseDTO;
import com.jcalvopinam.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author juan.calvopina
 */
@RestController
@RequestMapping(value = "/persons")
@Slf4j
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> findAllPersons() {
        log.info("Find all persons");
        return personService.findAll();
    }

    @GetMapping("/{text}")
    public PersonResponseDTO findByText(@PathVariable final String text) {
        log.info(String.format("Finding by: %s", text));
        return personService.findByText(text, text, text);
    }

    @PostMapping
    public PersonResponseDTO savePerson(@RequestBody final PersonRequestDTO personRequestDTO) {
        log.info(String.format("Saving person: %s", personRequestDTO.toString()));
        return personService.save(personRequestDTO);
    }

    @PutMapping("/{id}")
    public PersonResponseDTO updatePerson(@RequestBody final PersonRequestDTO personRequestDTO, @PathVariable final int id) {
        Validate.notNull(personRequestDTO, "The person cannot be null");
        log.info(String.format("Updating person: %s", personRequestDTO.toString()));
        return personService.update(personRequestDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable final int id) {
        log.info(String.format("Deleting person: %s", id));
        personService.deleteById(id);
    }

}