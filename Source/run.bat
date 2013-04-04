
::for /l %%a in (1, 1, 100) do ( java Examples.Mailbox.Main )
::for /l %%a in (1, 1, 100) do ( java Examples.File_Manager.Main )
::for /l %%a in (1, 1, 100) do ( java Examples.Resource_Manager.Main )

java Examples.Mailbox.Main
java Examples.File_Manager.Main
java Examples.Resource_Manager.Main