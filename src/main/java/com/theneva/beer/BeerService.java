package com.theneva.beer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class BeerService
{
    @Autowired
    private BeerRepository beerRepository;

    @RequestMapping(path = "/beers", method = RequestMethod.GET)
    public Iterable<Beer> all()
    {
        return beerRepository.findAll();
    }

    @RequestMapping(path = "/beers", method = RequestMethod.POST)
    public Beer save(@RequestBody Beer beer)
    {
        beerRepository.save(beer);
        return beer;
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
