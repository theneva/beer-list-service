package com.theneva.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class BeerService
{
    @Autowired
    private BeerRepository beerRepository;

    @RequestMapping(path = "/beers", method = RequestMethod.GET)
    public ResponseEntity all(
            @RequestParam(name = "id", required = false) final String idQuery)
    {
        if (idQuery == null)
        {
            return ResponseEntity.ok(beerRepository.findAll());
        }

        try
        {
            final List<Long> ids = Arrays.stream(idQuery.split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(beerRepository.findAll(ids));
        }
        catch (final NumberFormatException e)
        {
            return ResponseEntity.badRequest()
                    .body("IDs must be longs separed by commas.");
        }
    }

    @RequestMapping(path = "/beers", method = RequestMethod.POST)
    public Beer save(@RequestBody Beer beer)
    {
        beerRepository.save(beer);
        return beer;
    }

    @RequestMapping(path = "/beers/{id}", method = RequestMethod.GET)
    public Beer byId(@PathVariable("id") final long id)
    {
        return beerRepository.findOne(id);
    }

    @RequestMapping(path = "/beers/create-hack", method = RequestMethod.GET)
    public Iterable<Beer> createHack()
    {
        final List<Beer> beers = Arrays.asList(
                new Beer("Amundsen Pale Ale", "Pale Ale", "Norway's best Pale Ale"),
                new Beer("Stella Artois", "Lager", "Also known as \"The Wifebeater\""),
                new Beer("Brooklyn East IPA", "India Pale Ale", "Brooklyn has done it again."),
                new Beer("Run", "To your water, baby", "Run")
        );

        return beerRepository.save(beers);
    }
}
