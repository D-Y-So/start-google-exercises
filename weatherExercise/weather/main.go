package main

import (
	"fmt"

	"exercise.go/timeanddate"
)

func main() {
	var timeAndDateScraper timeanddate.TimeAndDateScraper = timeanddate.NewTimeAndDateScraper("israel", "haifa")
	fmt.Println(timeAndDateScraper.WeatherSummary())

}
