
del Mailbox-Printout.txt
del Lettori_Scrittori-Printout.txt

:: for /l %%a in (1, 1, 100) do (java Examples.Mailbox.Main >> Mailbox-Printout.txt)
:: for /l %%a in (1, 1, 100) do (java Examples.Lettori_Scrittori.Main >> Lettori_Scrittori-Printout.txt)

java Examples.Mailbox.Main > Mailbox-Printout.txt
::java Examples.Lettori_Scrittori.Main > Lettori_Scrittori-Printout.txt