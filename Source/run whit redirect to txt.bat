
del Mailbox-Printout.txt
del File_Manager-Printout.txt
del Resource_Manager-Printout.txt

for /l %%a in (1, 1, 100) do (java Examples.Mailbox.Main >> Mailbox-Printout.txt)
for /l %%a in (1, 1, 100) do (java Examples.File_Manager.Main >> File_Manager-Printout.txt)
for /l %%a in (1, 1, 100) do (java Examples.Resource_Manager.Main >> Resource_Manager-Printout.txt)

:: java Examples.Mailbox.Main > Mailbox-Printout.txt
:: java Examples.File_Manager.Main > File_Manager-Printout.txt
:: java Examples.Resource_Manager.Main > Resource_Manager-Printout.txt


