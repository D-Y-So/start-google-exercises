package timeanddate

import (
	"fmt"
	"time"

	"github.com/gocolly/colly"
)

const SITE_URL = "https://www.timeanddate.com/weather/"

type TimeAndDateScraper struct {
	country string
	city    string
}

func NewTimeAndDateScraper(country string, city string) TimeAndDateScraper {
	return TimeAndDateScraper{country, city}
}

type WeatherSummaryStruct struct {
	minTemperature, maxTemperature, precipitation, wind, humidity string
}

func (ws WeatherSummaryStruct) String() string {
	return fmt.Sprintf("Weather summary for %s :\n Min temperature: %v \n Max Temperature: %v \n Precipitation: %v \n Wind: %v \n Humidity: %v \n", time.Now().Format("2006-January-02 15:04:05"), ws.minTemperature, ws.maxTemperature, ws.precipitation, ws.wind, ws.humidity)
}

func (sc *TimeAndDateScraper) WeatherSummary() WeatherSummaryStruct {
	c := colly.NewCollector()

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("Scraping:", r.URL)
	})
	c.OnResponse(func(r *colly.Response) {
		fmt.Println("Status:", r.StatusCode)
	})

	var nowWeather []string
	c.OnHTML("table#wt-48 > tbody", func(h *colly.HTMLElement) {
		h.ForEach("tr", func(_ int, el *colly.HTMLElement) {
			nowWeather = append(nowWeather, el.ChildText("td:nth-child(2)"))
		})
	})

	c.OnError(func(r *colly.Response, err error) {
		fmt.Println("Request URL:", r.Request.URL, "failed with response:", r, "\nError:", err)
	})

	c.Visit(SITE_URL + sc.country + "/" + sc.city)

	//sc.minMaxTemperature()

	return WeatherSummaryStruct{nowWeather[1], nowWeather[1], nowWeather[9], nowWeather[4], nowWeather[6]}
}

/*
func (sc *TimeAndDateScraper) minMaxTemperature() []string {
	c := colly.NewCollector()

	c.OnRequest(func(r *colly.Request) {
		fmt.Println("Scraping:", r.URL)
	})
	c.OnResponse(func(r *colly.Response) {
		fmt.Println("Status:", r.StatusCode)
	})

	var hourlyTemp []string
	count := 0
	maxTimePlace := 0
	c.OnHTML("table#wt-hbh > tbody", func(h *colly.HTMLElement) {
		h.ForEach("tr", func(_ int, el *colly.HTMLElement) {
			count = count + 1
			hourlyTemp = append(hourlyTemp, el.ChildText("td:nth-child(3)"))
			if el.ChildText("th") == "23:00" {
				maxTimePlace = count
			}
			fmt.Println(el.ChildText("td:nth-child(3)"))
		})
	})

	c.OnError(func(r *colly.Response, err error) {
		fmt.Println("Request URL:", r.Request.URL, "failed with response:", r, "\nError:", err)
	})

	c.Visit(SITE_URL + sc.country + "/" + sc.city + "/hourly")
	hourlyTemp = hourlyTemp[:maxTimePlace]
	var minMax []string

	for i := 0; i < len(hourlyTemp); i++ {

	}

	minMax = append(minMax, "1")
	return minMax
}
*/
