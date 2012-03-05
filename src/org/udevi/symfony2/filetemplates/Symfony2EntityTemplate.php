<?php
namespace ${namespaceDir};

use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass="Udevi\ChurchBundle\Repository\AddressRepository")
 * @ORM\Table(name="${optionalTableName}")
 */
class ${name} {
    
    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $id;
    
    //put your code here
}
?>