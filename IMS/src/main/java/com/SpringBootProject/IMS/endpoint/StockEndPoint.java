package com.SpringBootProject.IMS.endpoint;

import com.SpringBootProject.IMS.entity.StockTable;
import com.SpringBootProject.IMS.service.StockService;
import com.SpringBootProject.IMS.valueobject.Stock;
import com.SpringBootProject.IMS.valueobject.TemporaryStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//CRUD OPERATIONS FOR STOCKS

@RestController
@CrossOrigin(origins = "*")
public class StockEndPoint {

    @Autowired
    StockService stockService;

//    @PostMapping(value = "/create/stock")
//    public StockTable stockCreation(@RequestBody Stock stock)
//    {
//
//        return this.stockService.stockCreate(stock);
//    }
@PostMapping(value = "/create/stock")
public String stockCreation(@RequestBody Stock stock)
{
    this.stockService.stockCreate(stock);
    System.out.println("Name"+stock.getStockName());
    return "Stock Created Successfully";
}
//    @GetMapping(value = "/display/stock")
//    public List<StockTable> stockDisplayAll()
//    {
//        return this.stockService.displayAll();
//    }
   @GetMapping(value = "/search/stock/{stockName}")
    public String stockSearching(@PathVariable String stockName)
   {
        return this.stockService.stockSearch(stockName);
   }

   @PostMapping(value = "/update/stock")
    public StockTable stockUpdate(@RequestBody TemporaryStock temporaryStock)
   {
      return this.stockService.stockUpdate(temporaryStock);
   }

   @DeleteMapping(value = "delete/stock/{stockId}")
    public String stockDelete(@PathVariable Long stockId)
   {
       return this.stockService.stockDelete(stockId);
   }


   @GetMapping(value = "display/stock")
    public List<StockTable> stockDisplay()
   {
       return this.stockService.stockRead();
   }

   @GetMapping(value = "pagination/stock/{offset}/{pageSize}")
    public Page<StockTable> stocksPagination(@PathVariable int offset , @PathVariable int pageSize)
   {
    return this.stockService.stockPagination(offset, pageSize);
   }

    @GetMapping(value = "paginationAndSort/stock/{offset}/{pageSize}/{fieldName}")
    public Page<StockTable> stocksPaginationAndSort(@PathVariable int offset , @PathVariable int pageSize , @PathVariable String fieldName)
    {
        return this.stockService.stockPaginationAndSorting(offset, pageSize, fieldName);
    }
}
